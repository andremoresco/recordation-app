<template>
  <div id="page" class="vh-100">
    <v-row align-v="center" align="center">
      <b-container>
        <b-row class="justify-content-center">
          <h1>Bem-vindo</h1>
        </b-row>
        <b-row class="justify-content-center">
          Não perca nenhuma recordação de alguém próximo.
        </b-row>
      </b-container>

      <b-container>
        <b-form>
          <b-form-group id="cpf-group" label="Cpf" label-for="cpf-input">
            <b-form-input id="cpf-input" v-model="form.cpf" required>
            </b-form-input>
          </b-form-group>

          <b-form-group
            id="password-group"
            label="Senha"
            label-for="password-input"
          >
            <b-form-input
              id="password-input"
              v-model="form.password"
              type="password"
              required
            >
            </b-form-input>
          </b-form-group>

          <b-button block type="submit" variant="primary">Entrar</b-button>
          <b-button variant="link">Esqueceu sua senha?</b-button>
        </b-form>
      </b-container>
    </v-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      form: {
        cpf: "",
        password: "",
      },
    };
  },
};
</script>

<style lang="scss" scoped>
#page {
  padding: 10%;
}
</style>