/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { MxpmsSearchEquipmentMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix-dialog.component';
import { MxpmsSearchEquipmentMySuffixService } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.service';
import { MxpmsSearchEquipmentMySuffix } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.model';

describe('Component Tests', () => {

    describe('MxpmsSearchEquipmentMySuffix Management Dialog Component', () => {
        let comp: MxpmsSearchEquipmentMySuffixDialogComponent;
        let fixture: ComponentFixture<MxpmsSearchEquipmentMySuffixDialogComponent>;
        let service: MxpmsSearchEquipmentMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MxpmsSearchEquipmentMySuffixDialogComponent],
                providers: [
                    MxpmsSearchEquipmentMySuffixService
                ]
            })
            .overrideTemplate(MxpmsSearchEquipmentMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MxpmsSearchEquipmentMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MxpmsSearchEquipmentMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MxpmsSearchEquipmentMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.mxpmsSearchEquipment = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'mxpmsSearchEquipmentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new MxpmsSearchEquipmentMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.mxpmsSearchEquipment = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'mxpmsSearchEquipmentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
