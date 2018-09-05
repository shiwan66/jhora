/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { MxpmsSearchEquipmentMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix-detail.component';
import { MxpmsSearchEquipmentMySuffixService } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.service';
import { MxpmsSearchEquipmentMySuffix } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.model';

describe('Component Tests', () => {

    describe('MxpmsSearchEquipmentMySuffix Management Detail Component', () => {
        let comp: MxpmsSearchEquipmentMySuffixDetailComponent;
        let fixture: ComponentFixture<MxpmsSearchEquipmentMySuffixDetailComponent>;
        let service: MxpmsSearchEquipmentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MxpmsSearchEquipmentMySuffixDetailComponent],
                providers: [
                    MxpmsSearchEquipmentMySuffixService
                ]
            })
            .overrideTemplate(MxpmsSearchEquipmentMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MxpmsSearchEquipmentMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MxpmsSearchEquipmentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new MxpmsSearchEquipmentMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.mxpmsSearchEquipment).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
