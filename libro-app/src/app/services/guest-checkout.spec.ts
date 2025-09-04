import { TestBed } from '@angular/core/testing';

import { GuestCheckoutService } from './guest-checkout';

describe('GuesCheckoutService', () => {
  let service: GuestCheckoutService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GuestCheckoutService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
