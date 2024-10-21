import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

public class CurrencyConverterGUI {

    private static final String API_KEY = "2bae9a266da7e6dc0f5892bc"; // Tu clave API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public static void main(String[] args) {
        // Crear la ventana principal
        JFrame frame = new JFrame("Conversor de Monedas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        // Crear los elementos de la interfaz
        JLabel labelAmount = new JLabel("Monto:");
        JTextField textAmount = new JTextField(10);

        JLabel labelFromCurrency = new JLabel("De:");
        JLabel labelToCurrency = new JLabel("A:");

        // Listado de monedas para seleccionar
        String[] currencies = {"USD", "EUR", "CLP", "BRL", "CAD", "COP"};
        JComboBox<String> comboBoxFrom = new JComboBox<>(currencies);
        JComboBox<String> comboBoxTo = new JComboBox<>(currencies);

        JButton buttonConvert = new JButton("Convertir");
        JLabel resultLabel = new JLabel("Resultado:");

        // Crear el panel y agregar los componentes
        JPanel panel = new JPanel();
        panel.add(labelAmount);
        panel.add(textAmount);
        panel.add(labelFromCurrency);
        panel.add(comboBoxFrom);
        panel.add(labelToCurrency);
        panel.add(comboBoxTo);
        panel.add(buttonConvert);
        panel.add(resultLabel);

        // Personalización de colores
        panel.setBackground(Color.ORANGE);  // Color de fondo del panel
        labelAmount.setForeground(Color.BLACK);  // Color del texto de la etiqueta
        labelFromCurrency.setForeground(Color.BLACK);
        labelToCurrency.setForeground(Color.BLACK);
        resultLabel.setForeground(Color.BLACK);  // Color del texto de resultado

        // Personalización de fuentes
        Font font = new Font("Arial", Font.BOLD, 16);
        labelAmount.setFont(font);
        labelFromCurrency.setFont(font);
        labelToCurrency.setFont(font);
        buttonConvert.setFont(new Font("Arial", Font.BOLD, 14));
        resultLabel.setFont(new Font("Arial", Font.ITALIC, 18));



        // Agregar el panel a la ventana principal
        frame.add(panel);
        frame.setVisible(true);

        // Acción cuando el usuario hace clic en el botón "Convertir"
        buttonConvert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el monto y las monedas seleccionadas
                    double amount = Double.parseDouble(textAmount.getText());
                    String fromCurrency = (String) comboBoxFrom.getSelectedItem();
                    String toCurrency = (String) comboBoxTo.getSelectedItem();

                    // Realizar la solicitud HTTP a la API para la moneda de origen seleccionada
                    String apiUrl = BASE_URL + fromCurrency;
                    HttpClient client = HttpClient.newHttpClient();
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(apiUrl))
                            .build();
                    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                    // Parsear la respuesta JSON
                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONObject conversionRates = jsonObject.getJSONObject("conversion_rates");

                    // Obtener la tasa de cambio para la moneda de destino seleccionada
                    double rate = conversionRates.getDouble(toCurrency);

                    // Calcular la conversión
                    double convertedAmount = amount * rate;

                    // Mostrar el resultado en la etiqueta
                    resultLabel.setText(String.format("Resultado: %.2f %s", convertedAmount, toCurrency));
                } catch (IOException | InterruptedException ex) {
                    resultLabel.setText("Error al conectar con la API.");
                    ex.printStackTrace();
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Por favor, ingresa un número válido.");
                }
            }
        });
    }
}
