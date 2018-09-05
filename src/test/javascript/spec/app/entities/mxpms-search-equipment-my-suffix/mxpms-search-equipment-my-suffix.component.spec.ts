/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { MxpmsSearchEquipmentMySuffixComponent } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.component';
import { MxpmsSearchEquipmentMySuffixService } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.service';
import { MxpmsSearchEquipmentMySuffix } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.model';

describe('Component Tests', () => {

    describe('MxpmsSearchEquipmentMySuffix Management Component', () => {
        let comp: MxpmsSearchEquipmentMySuffixComponent;
        let fixture: ComponentFixture<MxpmsSearchEquipmentMySuffixComponent>;
        let service: MxpmsSearchEquipmentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MxpmsSearchEquipmentMySuffixComponent],
                providers: [
                    MxpmsSearchEquipmentMySuffixService
                ]
            })
            .overrideTemplate(MxpmsSearchEquipmentMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MxpmsSearchEquipmentMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MxpmsSearchEquipmentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new MxpmsSearchEquipmentMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.mxpmsSearchEquipments[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
