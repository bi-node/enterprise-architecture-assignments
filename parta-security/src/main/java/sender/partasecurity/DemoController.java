package sender.partasecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/shop")
    public String shop() {
        return "This is the shop..Hi everyone you are welcome";
    }

    @GetMapping("/orders")
    public String orders() {
        return "This is the orders...Only employee of this organization is allowed here";
    }

    @GetMapping("/payments")
    public String payments() {
        return "This is the payments...Only employees of finance department works here";
    }
}
