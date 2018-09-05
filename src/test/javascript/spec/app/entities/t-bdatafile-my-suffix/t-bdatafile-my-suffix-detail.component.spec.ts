/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { TBdatafileMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix-detail.component';
import { TBdatafileMySuffixService } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix.service';
import { TBdatafileMySuffix } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix.model';

describe('Component Tests', () => {

    describe('TBdatafileMySuffix Management Detail Component', () => {
        let comp: TBdatafileMySuffixDetailComponent;
        let fixture: ComponentFixture<TBdatafileMySuffixDetailComponent>;
        let service: TBdatafileMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TBdatafileMySuffixDetailComponent],
                providers: [
                    TBdatafileMySuffixService
                ]
            })
            .overrideTemplate(TBdatafileMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TBdatafileMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TBdatafileMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TBdatafileMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tBdatafile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
