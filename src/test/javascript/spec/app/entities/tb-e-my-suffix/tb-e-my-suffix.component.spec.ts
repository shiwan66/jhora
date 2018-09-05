/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { TbEMySuffixComponent } from '../../../../../../main/webapp/app/entities/tb-e-my-suffix/tb-e-my-suffix.component';
import { TbEMySuffixService } from '../../../../../../main/webapp/app/entities/tb-e-my-suffix/tb-e-my-suffix.service';
import { TbEMySuffix } from '../../../../../../main/webapp/app/entities/tb-e-my-suffix/tb-e-my-suffix.model';

describe('Component Tests', () => {

    describe('TbEMySuffix Management Component', () => {
        let comp: TbEMySuffixComponent;
        let fixture: ComponentFixture<TbEMySuffixComponent>;
        let service: TbEMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbEMySuffixComponent],
                providers: [
                    TbEMySuffixService
                ]
            })
            .overrideTemplate(TbEMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbEMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbEMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TbEMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tbES[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
