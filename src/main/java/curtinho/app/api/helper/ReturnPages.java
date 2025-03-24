package curtinho.app.api.helper;

import lombok.Data;

@Data
public class ReturnPages {

    public String notFoundPage(){
        return """
                <!DOCTYPE html>
                <html lang="pt-BR">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>404 - Link Não Encontrado</title>
                    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
                    <style>
                        * {
                            margin: 0;
                            padding: 0;
                            box-sizing: border-box;
                        }

                        body {
                            font-family: 'Poppins', sans-serif;
                            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
                            min-height: 100vh;
                            display: flex;
                            flex-direction: column;
                            align-items: center;
                            justify-content: center;
                            padding: 20px;
                        }

                        .container {
                            text-align: center;
                            background: white;
                            padding: 40px;
                            border-radius: 20px;
                            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
                            max-width: 500px;
                            width: 100%;
                        }

                        .error-code {
                            font-size: 120px;
                            font-weight: 600;
                            color: #2d3436;
                            line-height: 1;
                            margin-bottom: 20px;
                            text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.1);
                        }

                        .error-message {
                            font-size: 24px;
                            color: #2d3436;
                            margin-bottom: 30px;
                        }

                        .error-description {
                            color: #636e72;
                            margin-bottom: 30px;
                            line-height: 1.6;
                        }

                        @media (max-width: 480px) {
                            .container {
                                padding: 20px;
                            }

                            .error-code {
                                font-size: 80px;
                            }

                            .error-message {
                                font-size: 20px;
                            }
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1 class="error-code">404</h1>
                        <h2 class="error-message">Link Não Encontrado</h2>
                        <p class="error-description">
                            O link que você está procurando expirou ou não existe.
                            Por favor, verifique o URL e tente novamente.
                        </p>
                    </div>
                </body>
                </html>
                """;
    }
}
