package at.wrk.web;

import at.wrk.model.Benutzer;
import at.wrk.pdf.MyPdfView;
import at.wrk.service.PDFService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PDFController {

    @Autowired
    private PDFService pdfService;

    @RequestMapping(path = "/report", method = RequestMethod.GET)
    public ModelAndView report() {
        
        Map<String, Object> model = new HashMap<>();

        List<Benutzer> benutzer = pdfService.findAll();
        model.put("benutzer", benutzer);

        return new ModelAndView(new MyPdfView(), model);
    }
}