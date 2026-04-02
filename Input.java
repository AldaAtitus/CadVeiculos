public class Input {
    // Lê um número inteiro válido
    public static int scanInt(String message) {
        while (true) {
            String entrada = IO.readln(message);
            if (entrada == null)
                continue;
            
            entrada = entrada.trim();
            
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                IO.println("Valor inválido! Digite um número inteiro.");
            }
        }
    }
}