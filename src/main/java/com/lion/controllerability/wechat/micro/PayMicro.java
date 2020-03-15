package com.lion.controllerability.wechat.micro;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * <Description> <br>
 *
 * @author zhang.xianhao<br>
 * @version 1.0<br>
 * @CreateDate 2019/12/16 17:12 <br>
 * @see com.lion.controllerability.wechat.micro <br>
 * @since R9.0<br>
 */
@ApiIgnore
@RestController
@RequestMapping("/microservice/pay")
public final class PayMicro {


    @PostMapping("/callback")
    public void callback(HttpServletRequest request) {

        InputStream is = null;
        try {
            is = request.getInputStream();
            StringBuilder sb = new StringBuilder();
            byte[] b = new byte[4096];
            for (int n; (n = is.read(b)) != -1; ) {
                sb.append(new String(b, 0, n));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return;
    }

}
