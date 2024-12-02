import {Component, inject} from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import {CommonModule} from "@angular/common";
import {ContactService} from "../../data-access/contact.service";
import {Contact} from "../../data-access/contact.model";

@Component({
  selector: 'app-contact-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './contact-form.component.html',
  styleUrl: './contact-form.component.css'
})
export class ContactFormComponent {

  contactForm: FormGroup;
  private contactService = inject(ContactService);
  private fb = inject(FormBuilder);
  constructor() {
    this.contactForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      message: ['', [Validators.required, Validators.maxLength(300)]],
    });
  }

  onSubmit() {
    if (this.contactForm.invalid) {
      alert('Veuillez remplir tous les champs correctement');
      return;
    }
    const contact = this.createFromForm();
    this.contactService.create(contact).subscribe({
      next: (resp)=>{
        if(resp.body){
          alert('Demande de contact envoyée avec succès');
          this.contactForm.reset();
        }
      },
      error: (err)=>{
        alert('Votre message ne peut pas etre envoyer, vérifier votre connexion');
      }
    })

  }



  private createFromForm():Contact{
    return {
      email : this.contactForm.get('email')!.value,
      message: this.contactForm.get('message')!.value
    }
  }
}
