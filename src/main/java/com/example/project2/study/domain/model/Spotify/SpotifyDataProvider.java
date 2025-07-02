package com.example.project2.study.domain.model.Spotify;

public class SpotifyDataProvider {

    public static String getArtistas() {
        return """
                [
                    {
                        "descritivo": "Henrique & Juliano",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab676161000051744dcd8a3bff84cd7703892cf4"
                    },
                    {
                        "descritivo": "Diego & Victor Hugo",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab67616100005174713d2a5853dcb4e8dc06b6c9"
                    },
                    {
                        "descritivo": "Henrique & Juliano",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab676161000051744dcd8a3bff84cd7703892cf4"
                    },
                    {
                        "descritivo": "Diego & Victor Hugo",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab67616100005174713d2a5853dcb4e8dc06b6c9"
                    },
                    {
                        "descritivo": "Henrique & Juliano",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab676161000051744dcd8a3bff84cd7703892cf4"
                    },
                    {
                        "descritivo": "Diego & Victor Hugo",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab67616100005174713d2a5853dcb4e8dc06b6c9"
                    },
                    {
                        "descritivo": "Henrique & Juliano",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab676161000051744dcd8a3bff84cd7703892cf4"
                    },
                    {
                        "descritivo": "Diego & Victor Hugo",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab67616100005174713d2a5853dcb4e8dc06b6c9"
                    },
                    {
                        "descritivo": "Henrique & Juliano",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab676161000051744dcd8a3bff84cd7703892cf4"
                    },
                    {
                        "descritivo": "Diego & Victor Hugo",
                        "subtitulo": "Artista",
                        "urlImagem": "https://i.scdn.co/image/ab67616100005174713d2a5853dcb4e8dc06b6c9"
                    }
                ]
                """;
    }

    public static String getSugestoesPlayList() {
        return """
            [
                {
                    "title": "Crie sua primeira playlist",
                    "subtitle": "É Fácil, vamos te ajudar.",
                    "sugestao": "Criar playlist"
                },
                {
                    "title": "Que tal seguir um podcast novo?",
                    "subtitle": "Avisaremos você sobre novos episódios.",
                    "sugestao": "Explore podcasts"
                }
            ]
            """;
    }


    public static String getSpotifyLinks() {
        return """
            [
                { "title": "Legal", "route": "" },
                { "title": "Segurança e centro de privacidade", "route": "" },
                { "title": "Politica de privacidade", "route": "" },
                { "title": "Cookies", "route": "" },
                { "title": "Sobre anúncios", "route": "" },
                { "title": "Acessibilidade", "route": "" }
            ]
            """;
    }

    public static String getPlayListCards() {
        return """
            [
                {
                    "title": "Musicas em Alta",
                    "hasBorder": false,
                    "itens": [
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e020367e148e99c4e09d3bb4926",
                            "title": "Camisola e Fio - Ao Vivo",
                            "subTitle": "Diego & Victor Hugo, Lauana Prado"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e0237f1ef969a18127c546a9a02",
                            "title": "Tipo Nino vs Kabrinha",
                            "subTitle": "DJ Biel Divulga, DK Dozabri, Mc Lekão"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e02923c641bf30fe1563a168d44",
                            "title": "Princesa - Ao Vivo",
                            "subTitle": "Gustavo Mioto, Ana Castela"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e02dee23f5771e3d55c5f41278c",
                            "title": "Saudade Proibida - Ao Vivo",
                            "subTitle": "Simone Mendes"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e021f50fc82d780d1d935bbddd7",
                            "title": "Gabriela",
                            "subTitle": "KATSEYE"
                        },
                         {
                            "image": "https://i.scdn.co/image/ab67616d00001e021f50fc82d780d1d935bbddd7",
                            "title": "TESTE CARROSSEL",
                            "subTitle": "KATSEYE"
                        }
                    ]
                },
                {
                    "title": "Artistas Populares",
                    "hasBorder": true,
                    "itens": [
                        {
                            "image": "https://i.scdn.co/image/ab676161000051744dcd8a3bff84cd7703892cf4",
                            "title": "Henrique & Juliano",
                            "subTitle": "Artista"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616100005174713d2a5853dcb4e8dc06b6c9",
                            "title": "Diego & Victor Hugo",
                            "subTitle": "Artista"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616100005174a07c8e41774e3bd6b20f13e3",
                            "title": "Grupo Menos É Mais",
                            "subTitle": "Artista"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab6761610000517477937baabc49dea13c17c706",
                            "title": "Jorge & Mateus",
                            "subTitle": "Artista"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab6761610000517477937baabc49dea13c17c706",
                            "title": "TESTE CARROSSEL",
                            "subTitle": "Artista"
                        }
                    ]
                },
                 {
                    "title": "Singles e álbuns que todo mundo gosta",
                    "hasBorder": false,
                    "itens": [
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e02ca0b1500e39626b9bba791a3",
                            "title": "White Noise (Sleep & Relaxation Sounds)",
                            "subTitle": "Sleepy John"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e022774b00531d558bc19e12a24",
                            "title": "Manifesto Musical 2 (Ao Vivo / Vol.1)",
                            "subTitle": "Henrique & Juliano"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e02b0860cf0a98e09663c82290c",
                            "title": "MAYHEM",
                            "subTitle": "Lady Gaga"
                        },
                        {
                            "image": "https://i.scdn.co/image/ab67616d00001e0263ecdc2fc549275b51fbb9a7",
                            "title": "333",
                            "subTitle": "Matuê"
                        }
                    ]
                }
                ]
            """;
    }


    public static String getFooterCards() {
        return """
            [
                {
                 "title": "Empresa",
                 "subtitles": ["Sobre", "Empregos","For the Record"]
                },
                {
                 "title": "Comunidades",
                  "subtitles": ["Para Artistas", "Desenvolvedores", "Publicidade","Investidores","Fornecedores"]
                },
                  {
                 "title": "Links úteis",
                  "subtitles": ["Suporte", "Aplicativo móvel grátis", "Popular por país"]
                },
                  {
                 "title": "Planos do Spotify",
                  "subtitles": ["Premium Individual", "Premium Duo", "Premium Família", "Premium Universitário", "Spotify Free"]
                }
            ]
            """;
    }
}
