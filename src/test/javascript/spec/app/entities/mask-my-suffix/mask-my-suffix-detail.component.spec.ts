/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { MaskMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix-detail.component';
import { MaskMySuffixService } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix.service';
import { MaskMySuffix } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix.model';

describe('Component Tests', () => {

    describe('MaskMySuffix Management Detail Component', () => {
        let comp: MaskMySuffixDetailComponent;
        let fixture: ComponentFixture<MaskMySuffixDetailComponent>;
        let service: MaskMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MaskMySuffixDetailComponent],
                providers: [
                    MaskMySuffixService
                ]
            })
            .overrideTemplate(MaskMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MaskMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MaskMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MaskMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.mask).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
