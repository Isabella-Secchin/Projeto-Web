import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AreaProtegida } from './area-protegida';

describe('AreaProtegida', () => {
  let component: AreaProtegida;
  let fixture: ComponentFixture<AreaProtegida>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AreaProtegida],
    }).compileComponents();

    fixture = TestBed.createComponent(AreaProtegida);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
