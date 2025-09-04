import { TestBed } from '@angular/core/testing';

import { GuesCheckout } from './gues-checkout';

describe('GuesCheckout', () => {
  let service: GuesCheckout;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuesCheckout);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
