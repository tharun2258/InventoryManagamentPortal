import { TestBed } from '@angular/core/testing';
import { CanActivateFn } from '@angular/router';

import { roleUserGuard } from './role-user.guard';

describe('roleUserGuard', () => {
  const executeGuard: CanActivateFn = (...guardParameters) => 
      TestBed.runInInjectionContext(() => roleUserGuard(...guardParameters));

  beforeEach(() => {
    TestBed.configureTestingModule({});
  });

  it('should be created', () => {
    expect(executeGuard).toBeTruthy();
  });
});
