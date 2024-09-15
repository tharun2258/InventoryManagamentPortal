import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { customInterceptor } from './custom.interceptor';
import { HeaderRowOutlet } from '@angular/cdk/table';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), provideRouter(routes),provideHttpClient(), provideAnimationsAsync(),provideHttpClient(withInterceptors([customInterceptor])),HeaderRowOutlet]
};
