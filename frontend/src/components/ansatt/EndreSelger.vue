<template>
  <div id="opprett-selger">
    <input v-model="selger.navn" placeholder="Selgernavn">
    <button v-on:click="endre">Endre selger</button>
    <p>{{ melding }}</p>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        selgernavn: "",
        melding: "",
        selger : {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      endre() {
        if (this.selgernavn.length == 0) {
          return;
        }
        axios
          .post('/ansatt', {
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
    },
  mounted() {
    axios.get('/ansatt/$route.params.id', {},
      {
        headers: {
          'Content-type': 'application/json',
        }
      }
    ).then(response => (this.selger = response.data))
  }
  }
</script>