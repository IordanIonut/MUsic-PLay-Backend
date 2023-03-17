package com.example.MUsicPLay.Configure;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
}
