/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { MxpmsSearchEquipmentMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix-delete-dialog.component';
import { MxpmsSearchEquipmentMySuffixService } from '../../../../../../main/webapp/app/entities/mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.service';

describe('Component Tests', () => {

    describe('MxpmsSearchEquipmentMySuffix Management Delete Component', () => {
        let comp: MxpmsSearchEquipmentMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<MxpmsSearchEquipmentMySuffixDeleteDialogComponent>;
        let service: MxpmsSearchEquipmentMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MxpmsSearchEquipmentMySuffixDeleteDialogComponent],
                providers: [
                    MxpmsSearchEquipmentMySuffixService
                ]
            })
            .overrideTemplate(MxpmsSearchEquipmentMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MxpmsSearchEquipmentMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MxpmsSearchEquipmentMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
