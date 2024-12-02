import { Injectable, inject } from "@angular/core";
import {Contact} from "./contact.model";
import {HttpClient, HttpResponse} from "@angular/common/http";
import { Observable } from "rxjs";
import {SERVER_URL} from "../../config/constants";
@Injectable({
    providedIn: "root",

}) export class ContactService {
    private readonly http = inject(HttpClient);
    private readonly path = SERVER_URL + "/api/contact";

    public create(contact: Contact): Observable<HttpResponse<Contact>> {
        return this.http.post<Contact>(this.path, contact, { observe : 'response'});
    }

}
