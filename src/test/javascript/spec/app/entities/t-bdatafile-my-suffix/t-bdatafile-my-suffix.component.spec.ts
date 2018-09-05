/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { TBdatafileMySuffixComponent } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix.component';
import { TBdatafileMySuffixService } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix.service';
import { TBdatafileMySuffix } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix.model';

describe('Component Tests', () => {

    describe('TBdatafileMySuffix Management Component', () => {
        let comp: TBdatafileMySuffixComponent;
        let fixture: ComponentFixture<TBdatafileMySuffixComponent>;
        let service: TBdatafileMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TBdatafileMySuffixComponent],
                providers: [
                    TBdatafileMySuffixService
                ]
            })
            .overrideTemplate(TBdatafileMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TBdatafileMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TBdatafileMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TBdatafileMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tBdatafiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
