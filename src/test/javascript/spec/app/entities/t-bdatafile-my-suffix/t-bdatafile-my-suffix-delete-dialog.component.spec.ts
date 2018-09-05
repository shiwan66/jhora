/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { TBdatafileMySuffixDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix-delete-dialog.component';
import { TBdatafileMySuffixService } from '../../../../../../main/webapp/app/entities/t-bdatafile-my-suffix/t-bdatafile-my-suffix.service';

describe('Component Tests', () => {

    describe('TBdatafileMySuffix Management Delete Component', () => {
        let comp: TBdatafileMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<TBdatafileMySuffixDeleteDialogComponent>;
        let service: TBdatafileMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TBdatafileMySuffixDeleteDialogComponent],
                providers: [
                    TBdatafileMySuffixService
                ]
            })
            .overrideTemplate(TBdatafileMySuffixDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TBdatafileMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TBdatafileMySuffixService);
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
