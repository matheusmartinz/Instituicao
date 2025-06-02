-- Para que serve um OF? --



Como transformar uma entidade em dto?                              // meu parametro
    public static T(Tipo do que eu quero retornar) nome_do_metodo (TDTO tdto) transforma o TDTO em T e retorna esse T.

Como transformar dados?
ex: Pessoa e Aluno;

public Pessoa transformarAlunoEmPessoa(Aluno aluno) {
Pessoa pessoa = new Pessoa();
pessoa.setId(aluno.getId());
pessoa.setUuid(aluno.getUuid());
pessoa.setNome(aluno.getNome());
pessoa.setCpf(aluno.getCpf());
pessoa.setEndereco(aluno.getEndereco());
return pessoa;
}

    public Pessoa transformarProfessorEmPessoa(Professor professor) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(professor.getId());
        pessoa.setUuid(professor.getUuid());
        pessoa.setNome(professor.getNome());
        pessoa.setCpf(professor.getCpf());
        pessoa.setEndereco(professor.getEndereco());
        return pessoa;
    }



-- Como é o funcionamento do .strem().map(Classe::of).toList() --
abre a array e percoe cada elemento dela, assim mapeando "quem vai receber" (Classe) 1 por 1 e voltando em uma list.


--- public ResponseEntity<SalaDTO> createSala(@RequestBody SalaDTO salaDTO) {
SalaDTO toReturn = salaService.createSala(salaDTO);
return ResponseEntity.status(HttpStatus.CREATED).body(toReturn);
} ---
to transformando o SalaDTO em uma entidade e criando um método "createSala" e passando como parâmetro o SalaDTO de informação
SalaDTO toReturn(objeto) vai receber chamando o objeto de SalaService.(método)createSala(o salaDTO)
e retornando a Response entidade vulgo toReturn. Porém toda via ao fazer isso no controller n vai ter o método feito no service, assim seguindo para lá.

ResponseEntity<O QUE EU QUERO RETORNAR> nome_do_metodo(@Como_estou_enviando_os_dados O que estou enviando) {
LOGICALOGICALOGICALOGICALOGICALOGICALOGICALOGICA
LOGICALOGICALOGICALOGICALOGICALOGICALOGICALOGICALOGICA
return ResponseEntity.status(<O QUE EU QUERO RETORNAR>.status(HttpStatus.O_QUE_EU_FIZ)).body(objeto_do_que_retornar);

return ResponseEntity(objeto).ok()

}



--  public SalaDTO createSala(SalaDTO salaDTO) {
Sala toSave = Sala(salaDTO);
Sala salvo = super.save(toSave);
return SalaDTO(salvo);
} --- 
estou pegando SalaDTO e criando um método createSala(q está sendo usado no controller), passando como parâmetro o SalaDTO objeto salaDTO
Classe Sala objeto toSave vai receber Sala(método chamado of) = (salaDTO) "objeto da classe SalaDTO"
vou criar outro objeto de Sala e salvar o toSave que está recebendo o salaDTO, assim retornando o valor o corpo
do SalaDTO(salvo "do valor salvo")

--  sala.capacidadeAlunos = OptionalNullable(salaDTO.getCapacidadeAlunos()).orElse(sala.getCapacidadeAlunos()); --
aqui, seria a funcionalidade de que, "caso salaDTO venha do front sem essa informação" usar o valor do model sala.getCapacidadeAlunos;

Nem toda classe que possuir DTO é uma entidade.