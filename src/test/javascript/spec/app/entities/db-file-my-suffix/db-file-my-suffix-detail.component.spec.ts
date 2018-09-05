/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { JhoraTestModule } from '../../../test.module';
import { DBFileMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix-detail.component';
import { DBFileMySuffixService } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix.service';
import { DBFileMySuffix } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix.model';

describe('Component Tests', () => {

    describe('DBFileMySuffix Management Detail Component', () => {
        let comp: DBFileMySuffixDetailComponent;
        let fixture: ComponentFixture<DBFileMySuffixDetailComponent>;
        let service: DBFileMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [DBFileMySuffixDetailComponent],
                providers: [
                    DBFileMySuffixService
                ]
            })
            .overrideTemplate(DBFileMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DBFileMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DBFileMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new DBFileMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.dBFile).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
