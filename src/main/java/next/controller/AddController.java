package next.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.dao.QuestionDao;
import next.model.Question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import core.utils.ServletRequestUtils;

public class AddController extends AbstractController {
	private static final Logger logger = LoggerFactory.getLogger(AddController.class);
	
	private QuestionDao questionDao = new QuestionDao();

	@Override
	public ModelAndView execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//long questionId = ServletRequestUtils.getRequiredLongParameter(request, "questionId");
		String writer = ServletRequestUtils.getStringParameter(request, "writer");
		String title = ServletRequestUtils.getStringParameter(request, "title");
		String contents = ServletRequestUtils.getStringParameter(request, "contents");

		questionDao.insert(new Question(writer, title, contents));
		
		ModelAndView mav = jstlView("redirect:/list.next");
		return mav;
	}
}