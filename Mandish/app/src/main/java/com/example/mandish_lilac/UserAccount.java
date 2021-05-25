package com.example.mandish_lilac;
/*
 사용자 계정 정보 모델 클래스
 */
public class UserAccount {
    private  String emailId; //Firebase Uid(고유 토근정보)
    private  String password; //이메일 아이디
    private  String idToken; //비밀번호

    public UserAccount(){} //파이어베이스에서는 생성자를 만들어야한다.

    public String getIdToken() {return idToken;}

    public void setIdToken(String idToken) {this.idToken = idToken;}

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
