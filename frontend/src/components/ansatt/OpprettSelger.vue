<template>
  <div id="opprett-selger">
    <input v-model="selgernavn" placeholder="Selgernavn">
    <button v-on:click="opprett">Opprett selger</button>
    <p>{{ melding }}</p>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        selgernavn: "",
        melding: ""
      }
    },
    methods: {
      opprett() {
        if (this.selgernavn.length == 0) {
          return;
        }
        axios
          .put('/ansatt', {
              navn: this.selgernavn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => (this.melding = response))

        this.melding = 'Selger ' + this.selgernavn + ' ble opprettet'

        console.log(this.melding)
      }
    }
  }
</script>