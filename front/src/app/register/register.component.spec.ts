import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterComponentTsComponent } from './register.component';

describe('RegisterComponentTsComponent', () => {
  let component: RegisterComponentTsComponent;
  let fixture: ComponentFixture<RegisterComponentTsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegisterComponentTsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RegisterComponentTsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
