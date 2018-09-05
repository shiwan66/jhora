/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { TbDMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix-delete-dialog.component';
import { TbDMySuffixService } from '../../../../../../main/webapp/app/entities/tb-d-my-suffix/tb-d-my-suffix.service';

describe('Component Tests', () => {

    describe('TbDMySuffix Management Delete Component', () => {
        let comp: TbDMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TbDMySuffixDeleteDialogComponent>;
        let service: TbDMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbDMySuffixDeleteDialogComponent],
                providers: [
                    TbDMySuffixService
                ]
            })
            .overrideTemplate(TbDMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbDMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbDMySuffixService);
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
