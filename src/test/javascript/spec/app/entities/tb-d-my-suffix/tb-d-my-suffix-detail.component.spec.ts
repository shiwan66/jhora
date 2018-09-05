/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { TbDMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix-detail.component';
import { TbDMySuffixService } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix.service';
import { TbDMySuffix } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix.model';

describe('Component Tests', () => {

    describe('TbDMySuffix Management Detail Component', () => {
        let comp: TbDMySuffixDetailComponent;
        let fixture: ComponentFixture<TbDMySuffixDetailComponent>;
        let service: TbDMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbDMySuffixDetailComponent],
                providers: [
                    TbDMySuffixService
                ]
            })
            .overrideTemplate(TbDMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbDMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbDMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TbDMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.tbD).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
