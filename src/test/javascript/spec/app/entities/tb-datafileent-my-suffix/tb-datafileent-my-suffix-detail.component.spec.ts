/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { TbDatafileentMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix-detail.component';
import { TbDatafileentMySuffixService } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.service';
import { TbDatafileentMySuffix } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.model';

describe('Component Tests', () => {

    describe('TbDatafileentMySuffix Management Detail Component', () => {
        let comp: TbDatafileentMySuffixDetailComponent;
        let fixture: ComponentFixture<TbDatafileentMySuffixDetailComponent>;
        let service: TbDatafileentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbDatafileentMySuffixDetailComponent],
                providers: [
                    TbDatafileentMySuffixService
                ]
            })
            .overrideTemplate(TbDatafileentMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbDatafileentMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbDatafileentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TbDatafileentMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tbDatafileent).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
