package br.com.hegom.mail.payload;

public record MailDto(String sender, String receiver, String subject, String text) {
}
