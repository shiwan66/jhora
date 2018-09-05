/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Observable';
import { JhiEventManager } from 'ng-jhipster';

import { JhoraTestModule } from '../../../test.module';
import { TbDatafileentMySuffixDialogComponent } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix-dialog.component';
import { TbDatafileentMySuffixService } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.service';
import { TbDatafileentMySuffix } from '../../../../../../main/webapp/app/entities/tb-datafileent-my-suffix/tb-datafileent-my-suffix.model';

describe('Component Tests', () => {

    describe('TbDatafileentMySuffix Management Dialog Component', () => {
        let comp: TbDatafileentMySuffixDialogComponent;
        let fixture: ComponentFixture<TbDatafileentMySuffixDialogComponent>;
        let service: TbDatafileentMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [TbDatafileentMySuffixDialogComponent],
                providers: [
                    TbDatafileentMySuffixService
                ]
            })
            .overrideTemplate(TbDatafileentMySuffixDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TbDatafileentMySuffixDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TbDatafileentMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TbDatafileentMySuffix(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.tbDatafileent = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tbDatafileentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new TbDatafileentMySuffix();
                        spyOn(service, 'create').and.returnValue(Observable.of(new HttpResponse({body: entity})));
                        comp.tbDatafileent = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'tbDatafileentListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
