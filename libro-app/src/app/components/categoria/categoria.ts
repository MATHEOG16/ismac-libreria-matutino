import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CategoriaService } from '../../services/categoria';
import Swal from 'sweetalert2';
import { NgForm } from '@angular/forms';
import { Categoria } from '../../model/categoria.model';

@Component({
  selector: 'app-categoria',
  standalone: false,
  templateUrl: './categoria.html',
  styleUrl: './categoria.css'
})
export class CategoriaComponent implements OnInit{

  categorias: Categoria[] = [];
  categoria: Categoria = { } as Categoria;
  editar: boolean = false;
  idEditar: number | null = null;

  dataSource!: MatTableDataSource<Categoria>;
  mostrarColumnas: String[] = ['idCategoria', 'categoria', 'descripcion', 'acciones'];

  @ViewChild('formularioCategoria') formularioCategoria!: ElementRef;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private categoriaService: CategoriaService){}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void{
    this.categoriaService.findAll().subscribe(data => { 
      this.dataSource = new MatTableDataSource(data);
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  save(): void{
    this.categoriaService.save(this.categoria).subscribe(() => {
      this.categoria = { } as Categoria;
      this.findAll();
    });
  }

  update(): void{
    if(this.idEditar !== null){
      this.categoriaService.update(this.idEditar, this.categoria).subscribe(() => {  
        this.categoria = { } as Categoria;
        this.editar = false;
        this.idEditar = null;
        this.findAll();
      });
    }
  }

  delete(categoria: Categoria): void {
  this.categoria = categoria;

  Swal.fire({
    title: '¿Desea eliminar el dato?',
    text: 'Esta acción no se puede deshacer',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Si, eliminar',
    cancelButtonText: 'Cancelar',
    confirmButtonColor: '#d33',
    cancelButtonColor: '#3085d6'
  }).then((result) => { 
    if(result.isConfirmed){
      this.categoriaService.delete(this.categoria.idCategoria).subscribe(() => {
        this.findAll();
        this.categoria = {} as Categoria;
        Swal.fire('Eliminado','La categoría ha sido eliminada','success');
      });
    } else {
      this.categoria = {} as Categoria;
    }
  });
}


  editarCategoria(categoria: Categoria): void{
    this.categoria = {...categoria};
    this.idEditar = categoria.idCategoria;
    this.editar = true;

    setTimeout(() => { 
      this.formularioCategoria.nativeElement.scrollIntoView({behavior: 'smooth', block: 'start'});
    }, 100);
  }

  editarCategoriaCancelar(form: NgForm): void{
    this.categoria = { } as Categoria;
    this.idEditar = null;
    this.editar = false;
    form.resetForm();
  }

  guardarCategoria(form: NgForm): void{
    if(this.editar && this.idEditar !== null){
      this.update();
      form.resetForm();
    }else{
      this.save();
      form.resetForm();
    }
  }

  buscarCategoria(event: Event){
    const filtro = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filtro.trim().toLowerCase();
  }
}
