/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JhoraTestModule } from '../../../test.module';
import { DBFileMySuffixComponent } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix.component';
import { DBFileMySuffixService } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix.service';
import { DBFileMySuffix } from '../../../../../../main/webapp/app/entities/db-file-my-suffix/db-file-my-suffix.model';

describe('Component Tests', () => {

    describe('DBFileMySuffix Management Component', () => {
        let comp: DBFileMySuffixComponent;
        let fixture: ComponentFixture<DBFileMySuffixComponent>;
        let service: DBFileMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [JhoraTestModule],
                declarations: [DBFileMySuffixComponent],
                providers: [
                    DBFileMySuffixService
                ]
            })
            .overrideTemplate(DBFileMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DBFileMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DBFileMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new DBFileMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.dBFiles[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
