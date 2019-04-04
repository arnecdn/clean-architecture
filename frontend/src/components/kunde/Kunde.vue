<template>
  <div>
    <input v-model="kundeTilLagring.navn" placeholder="Kundenavn">
    <button v-if="kundeTilLagring.id == ''" v-on:click.stop.prevent="opprettKunde">Opprett kunde</button>
    <button v-if="kundeTilLagring.id != ''" v-on:click.stop.prevent="lagreKunde(kundeTilLagring.id)">Lagre kunde</button>
    <p>{{ melding }}</p>
    <table>
      <tr>
        <td>Administrer</td>
        <td>Kundenavn</td>
      </tr>

      <tr v-for="kunde in kundee">
        <td>
          <button v-on:click="slettKunde(kunde.id)">Slett</button>
          <button v-on:click="hentKunde(kunde.id)">Endre</button>
        </td>
        <td>
          {{kunde.navn}}
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        kundee: [],
        kundeTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      tømFelter() {
        this.kundeTilLagring.id = ''
        this.kundeTilLagring.navn = ''
      },
      lagreKunde(id) {
        axios
          .put('/api/kunde/' + id,
            this.kundeTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Kunde er oppdatert!'
            this.hentAlle()
            this.tømFelter()
            this.$router.push('/kunde')
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettKunde() {

        axios
          .put('/api/kunde/', {
              navn: this.kundeTilLagring.navn
            }, {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Kunde er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettKunde(id) {
        axios.delete('/api/kunde/' + id, {},
          {}
        ).then(response => {
          this.kundee = response.data
          this.hentAlle()
        })
      },
      hentSalg(id) {
        axios.get('/api/salg/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kundeTilLagring = response.data))
      },
      hentKunde(id) {
        axios.get('/api/kunde/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kundeTilLagring = response.data))
      },
      hentAlle() {
        axios.get('/api/kunde', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.kundee = response.data))
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });

      },
    },

    mounted() {
      console.log('hent alle')
      this.hentAlle()
    }
  }
</script>