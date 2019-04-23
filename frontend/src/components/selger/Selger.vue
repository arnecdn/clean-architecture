<template>
  <div>
    <input v-model="selgerTilLagring.navn" placeholder="Selgernavn">
    <button v-if="selgerTilLagring.id == ''" v-on:click.stop.prevent="opprettSelger">Opprett selger</button>
    <button v-if="selgerTilLagring.id != ''" v-on:click.stop.prevent="lagreSelger(selgerTilLagring.id)">Lagre selger</button>

    <p>{{ melding }}</p>
    <table>
      <tr>
        <td>Administrer</td>
        <td>Selger navn</td>
      </tr>
      <tr v-for="selger in selgere">
        <td>
          <button v-on:click="slettSelger(selger.id)">Slett</button>
          <button v-on:click="hentAnsatt(selger.id)">Endre</button>
        </td>
        <td>
          {{selger.navn}}
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
        selgere: [],
        selgerTilLagring: {
          id: '',
          navn: ''
        }
      }
    },
    methods: {
      tømFelter() {
        this.selgerTilLagring.id = ''
        this.selgerTilLagring.navn = ''
      },
      lagreSelger(id) {
        axios
          .put('/api/selger/' + id,
            this.selgerTilLagring
            ,
            {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Selger er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      opprettSelger() {

        axios
          .put('/api/selger/',
            this.selgerTilLagring
            , {
              headers: {
                'Content-type': 'application/json'
              }
            }
          )
          .then(response => {
            this.melding = 'Selger er oppdatert!'
            this.hentAlle()
            this.tømFelter()
          })
          .catch(error => {
            this.melding = "Feil. Sjekk console"
            console.log(error.response)
          });
      },
      slettSelger(id) {
        axios.delete('/api/selger/' + id, {},
          {}
        ).then(response => {
          this.selgere = response.data
          this.hentAlle()
        })
      },
      hentAnsatt(id) {
        axios.get('/api/selger/' + id, {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgerTilLagring = response.data))
      },
      hentAlle() {
        axios.get('/api/selger', {},
          {
            headers: {
              'Content-type': 'application/json',
            }
          }
        ).then(response => (this.selgere = response.data))
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