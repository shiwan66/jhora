/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { TbDMySuffixComponent } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix.component';
import { TbDMySuffixService } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix.service';
import { TbDMySuffix } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix.model';

describe('Component Tests', () => {

    describe('TbDMySuffix Management Component', () => {
        let comp: TbDMySuffixComponent;
        let fixture: ComponentFixture<TbDMySuffixComponent>;
        let service: TbDMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbDMySuffixComponent],
                providers: [
                    TbDMySuffixService
                ]
            })
            .overrideTemplate(TbDMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbDMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbDMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TbDMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tbDS[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
