import Faculty.Exam;
import Faculty.GroupOperator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer
{
    private ServerSocket serverSocket;
    private final GroupOperator go = new GroupOperator();
    private static String goJSON;

    public void start(int port) throws IOException
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        go.addExam("11", new Exam("Дискретная математика", "Костенко К.И.",
                131, "11.01.2020", "10:00", 10, 0,
                "Можно досдать зачеты."));
        go.addExam("11", new Exam("Математический анализ", "Сеидова Н.М.",
                307, "15.01.2020", "08:00", 7, 1,
                "Лекциями пользуются те, кто их писал после проверки тетади, максимум 5 минут."));
        go.addExam("11", new Exam("Основы программирования", "Добровольская Н.Ю.",
                131, "20.01.2020", "09:00", 15, 0,
                "После подготовки будут доп. вопросы."));
        go.addExam("11", new Exam("Линейная алгебра", "Пелипенко Е.Ю.",
                305, "24.01.2020", "08:00", 5, 0,
                "На 3 - определения и теоремы без доказательств."));
        go.addExam("12", new Exam("Математический анализ", "Сеидова Н.М.",
                309, "11.01.2020", "10:00", 7, 1,
                "Лекциями пользуются те, кто их писал после проверки тетади, максимум 5 минут."));
        go.addExam("12", new Exam("Дискретная математика", "Костенко К.И.",
                129, "15.01.2020", "08:00", 10, 0,
                "Можно досдать зачеты."));
        go.addExam("12", new Exam("Линейная алгебра", "Пелипенко Е.Ю.",
                149, "20.01.2020", "08:00", 5, 0,
                "На 3 - определения и теоремы без доказательств."));
        go.addExam("12", new Exam("Основы программирования", "Добровольская Н.Ю.",
                101, "24.01.2020", "12:00", 15, 0,
                "После подготовки будут доп. вопросы."));
        goJSON = gson.toJson(go);

        serverSocket = new ServerSocket(port);
        while (true)
        {
            new EchoClientHandler(serverSocket.accept()).start();
        }
    }

    public void stop() throws IOException
    {
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread
    {
        private final Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run()
        {
            try
            {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            String inputLine = null;
            while (true)
            {
                try
                {
                    if ((inputLine = in.readLine()) == null) break;
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                if (".".equals(inputLine))
                {
                    out.println("bye");
                    break;
                }
                if ("{R}".equals(inputLine))
                {
                    out.println(goJSON);
                }
            }

            try
            {
                in.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            out.close();
            try
            {
                clientSocket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
