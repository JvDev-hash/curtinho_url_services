package curtinho.app.api.helper;

import lombok.Data;

@Data
public class ReturnPages {

    public String notFoundPage(){
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta http-equiv="X-UA-Compatible" content="IE=edge">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Document</title>
                </head>
                <style>
                    @import url('https://fonts.googleapis.com/css2?family=Poppins&display=swap');
                                
                    body {
                        font-family: 'Poppins', sans-serif;
                        display: flex;
                        flex-direction: column;
                        align-items: center;
                    }
                                
                    #http-number {
                        font-size: 10rem;
                        margin-bottom: 50px;
                    }
                                
                    #http-text {
                        font-size: x-large;
                        margin-top: -10px;
                    }
                </style>
                <body>
                    <h1 id="http-number">404</h1>
                    <p id="http-text">Link expirado ou inexistente!</p>
                </body>
                </html>
                """;
    }
}
