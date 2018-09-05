/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { TbEMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tb-e-my-suffix/tb-e-my-suffix-detail.component';
import { TbEMySuffixService } from '../../../../../../main/webapp/app/entities/tb-e-my-suffix/tb-e-my-suffix.service';
import { TbEMySuffix } from '../../../../../../main/webapp/app/entities/tb-e-my-suffix/tb-e-my-suffix.model';

describe('Component Tests', () => {

    describe('TbEMySuffix Management Detail Component', () => {
        let comp: TbEMySuffixDetailComponent;
        let fixture: ComponentFixture<TbEMySuffixDetailComponent>;
        let service: TbEMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbEMySuffixDetailComponent],
                providers: [
                    TbEMySuffixService
                ]
            })
            .overrideTemplate(TbEMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbEMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbEMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TbEMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tbE).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
