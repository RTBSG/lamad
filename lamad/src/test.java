
import org.springframework.util.StringUtils;
public class test {
        public static void main(String[] args) {
            //        取出手机号验证码
            String phone = "";
            String code = "loginVo.getCode()";
//        判断手机号和验证码是否为空
            if (!StringUtils.hasText(phone) || !StringUtils.hasText(code) ) {
                System.out.println("验证码错误");
                return;
            }
            System.out.println("验证码正确");
        }
    }


