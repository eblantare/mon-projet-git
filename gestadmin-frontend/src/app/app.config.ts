import { ApplicationConfig,importProvidersFrom,
   provideBrowserGlobalErrorListeners, provideZonelessChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';
import { FormsModule,  ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideRouter(routes), 
     provideHttpClient(withFetch()),
    // provideForms(), // ✅ suffit à inclure ReactiveFormsModule
      importProvidersFrom(
      MatInputModule,
      MatCardModule,
      MatButtonModule,
      FormsModule,
      ReactiveFormsModule,
      MatSelectModule,
      MatTableModule
    ),
    provideClientHydration(withEventReplay())
  ]
};
