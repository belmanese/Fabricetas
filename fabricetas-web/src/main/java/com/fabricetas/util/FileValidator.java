package com.fabricetas.util;

import com.fabricetas.domain.TransientFile;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by belman on 01/04/2017.
 */
@Component
public class FileValidator implements Validator {

    public boolean supports(Class<?> clazz) {
        return TransientFile.class.isAssignableFrom(clazz);
    }

    public void validate(Object obj, Errors errors) {
        TransientFile file = (TransientFile) obj;

        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}
