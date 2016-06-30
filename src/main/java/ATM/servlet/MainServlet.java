package ATM.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import ATM.Atm;
import ATM.AtmImpl;
import ATM.Archiver;

@WebServlet("/")
public class MainServlet extends HttpServlet {

    private Atm atm;
    private Archiver archiver;
    private final String error = "<b>ОШИБКА:</b> ";

    @Override
    public void init() throws ServletException {
        super.init();
        atm = new AtmImpl();
        archiver = new Archiver();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.equals("/")) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        } else if (action.startsWith("/deposit")) {
            int[] denominations = atm.getDenominations();
            req.setAttribute("denominations", denominations);
            req.getRequestDispatcher("deposit.jsp").forward(req, resp);

        } else if (action.startsWith("/withdraw")) {
            req.getRequestDispatcher("withdraw.jsp").forward(req, resp);

        } else if (action.startsWith("/balance")) {
            req.setAttribute("balance", atm.balance());
            req.setAttribute("status", atm.status().replaceAll("\n", "<br />"));
            req.getRequestDispatcher("balance.jsp").forward(req, resp);

        } else if (action.startsWith("/log")) {
            String currentUsersHomeDir = System.getProperty("user.home");
            String logFolder = currentUsersHomeDir + "/logs";

            try {
                archiver.zipFolder(logFolder, currentUsersHomeDir + "/logs.zip");
            } catch (Exception e) {
                req.setAttribute("message", error + e.getMessage());
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            }

            OutputStream out = resp.getOutputStream();
            FileInputStream in = new FileInputStream(currentUsersHomeDir + "/logs.zip");
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();

        } else {
            req.setAttribute("message", "Страница не найдена!");
            req.getRequestDispatcher("message.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = getAction(req);

        if (action.startsWith("/deposit")) {

            try {
                String[] selectedValue = req.getParameterValues("selectedValue");
                int denomination = Integer.parseInt(selectedValue[0]);
                int quantity;
                String quantityString = req.getParameter("quantity");
                if (quantityString.isEmpty() || !isNumeric(quantityString)) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(quantityString);
                }
                String result = atm.deposit(denomination, quantity);
                req.setAttribute("message", result.replaceAll("\n", "<br />"));
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", error + e.getMessage());
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            }
        }

        if (action.startsWith("/withdraw")) {
            try {
                int quantity;
                String quantityString = req.getParameter("quantity");
                if (quantityString.isEmpty() || !isNumeric(quantityString)) {
                    quantity = 0;
                } else {
                    quantity = Integer.parseInt(quantityString);
                }
                String result = atm.withdraw(quantity);
                req.setAttribute("message", result.replaceAll("\n", "<br />"));
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("message", error + e.getMessage());
                req.getRequestDispatcher("message.jsp").forward(req, resp);
            }
        }
    }

    private String getAction(HttpServletRequest req) {
        String requestURI = req.getRequestURI();
        return requestURI.substring(req.getContextPath().length(), requestURI.length());
    }

    public static boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
}
