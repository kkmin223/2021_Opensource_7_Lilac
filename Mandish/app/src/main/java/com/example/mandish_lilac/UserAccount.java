package com.example.mandish_lilac;
/*
 사용자 계정 정보 모델 클래스
 */
public class UserAccount {
    private String emailId; //Firebase Uid(고유 토근정보)
    private String password; //이메일 아이디
    private String idToken; //비밀번호
    private String nic;//닉네임
    private String name;//이름
    private RecipePost recipepost; // 사용자가 작성한 레시피정보
    private RecRecipe recrecipe;
    public class Write{
        private String write_code;

        public String getWrite_code() {
            return write_code;
        }

        public void setWrite_code(String write_code) {
            this.write_code = write_code;
        }
    }

    public UserAccount() {
    } //파이어베이스에서는 생성자를 만들어야한다.

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

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RecipePost getRecipepost() {
        return recipepost;
    }

    public void setRecipepost(RecipePost recipepost) {
        this.recipepost = recipepost;
    }

    public RecRecipe getRecrecipe() {
        return recrecipe;
    }

    public void setRecrecipe(RecRecipe recrecipe) {
        this.recrecipe = recrecipe;
    }
}
