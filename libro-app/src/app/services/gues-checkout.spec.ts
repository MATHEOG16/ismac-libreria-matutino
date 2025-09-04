import { TestBed } from '@angular/core/testing';

import { GuesCheckoutService } from './gues-checkout';

describe('GuesCheckoutService', () => {
  let service: GuesCheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuesCheckoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
