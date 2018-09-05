/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { TbDatafileentMySuffixComponent } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.component';
import { TbDatafileentMySuffixService } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.service';
import { TbDatafileentMySuffix } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.model';

describe('Component Tests', () => {

    describe('TbDatafileentMySuffix Management Component', () => {
        let comp: TbDatafileentMySuffixComponent;
        let fixture: ComponentFixture<TbDatafileentMySuffixComponent>;
        let service: TbDatafileentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbDatafileentMySuffixComponent],
                providers: [
                    TbDatafileentMySuffixService
                ]
            })
            .overrideTemplate(TbDatafileentMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbDatafileentMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbDatafileentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new TbDatafileentMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.tbDatafileents[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
