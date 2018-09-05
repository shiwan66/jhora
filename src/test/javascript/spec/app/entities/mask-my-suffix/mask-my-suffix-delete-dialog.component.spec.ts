/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { MaskMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix-delete-dialog.component';
import { MaskMySuffixService } from '../../../../../../main/webapp/app/entities/mask-my-suffix/mask-my-suffix.service';

describe('Component Tests', () => {

    describe('MaskMySuffix Management Delete Component', () => {
        let comp: MaskMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<MaskMySuffixDeleteDialogComponent>;
        let service: MaskMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [MaskMySuffixDeleteDialogComponent],
                providers: [
                    MaskMySuffixService
                ]
            })
            .overrideTemplate(MaskMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(MaskMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(MaskMySuffixService);
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
