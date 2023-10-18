package com.portalClientesPrimadera.Service;

import java.io.File;

public interface IEmailService {
    void sendEmail(String[] toUser, String subject, String message);
    void senEmailWithFile(String[] toUser, String subject, String message, File file);
}
