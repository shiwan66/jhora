/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { MaskMySuffixComponent } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix.component';
import { MaskMySuffixService } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix.service';
import { MaskMySuffix } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix.model';

describe('Component Tests', () => {

    describe('MaskMySuffix Management Component', () => {
        let comp: MaskMySuffixComponent;
        let fixture: ComponentFixture<MaskMySuffixComponent>;
        let service: MaskMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MaskMySuffixComponent],
                providers: [
                    MaskMySuffixService
                ]
            })
            .overrideTemplate(MaskMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MaskMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MaskMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MaskMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.masks[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
