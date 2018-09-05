/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { DBFileMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix-delete-dialog.component';
import { DBFileMySuffixService } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix.service';

describe('Component Tests', () => {

    describe('DBFileMySuffix Management Delete Component', () => {
        let comp: DBFileMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<DBFileMySuffixDeleteDialogComponent>;
        let service: DBFileMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [DBFileMySuffixDeleteDialogComponent],
                providers: [
                    DBFileMySuffixService
                ]
            })
            .overrideTemplate(DBFileMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DBFileMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DBFileMySuffixService);
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
