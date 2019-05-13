<template>

  <div>
    <div class="w3-container" id="menu">
      <div class="w3-content" style="max-width:700px">

        <h5 class="w3-center w3-padding-48"><span class="w3-tag w3-wide">Kunde administrasjon</span></h5>
        <div class="w3-container w3-padding-48 w3-card">
          <input v-model="kundeTilLagring.navn" placeholder="Selgernavn">
          <button v-if="kundeTilLagring.id == ''" v-on:click.stop.prevent="opprettKunde">Opprett kunde</button>
          <button v-if="kundeTilLagring.id != ''" v-on:click.stop.prevent="lagreKunde(kundeTilLagring.id)">Lagre kunde</button>
          <button v-if="kundeTilLagring.id != ''" v-on:click.stop.prevent="slettKunde(kundeTilLagring.id)">Slett</button>

          <button v-if="kundeTilLagring.id != ''" v-on:click.stop.prevent="tømFelter()">Tøm felter</button>
          <p>{{ melding }}</p>
        </div>
        <div class="w3-container w3-padding-48 w3-card">
          <div v-for="kunde in kunder">
            <a class="w3-button w3-block w3-white" v-on:click="hentKunde(kunde.id)">

              <div>
                <h5>{{kunde.navn}}</h5>
              </div>
            </a>
          </div>
        </div>

        <!--img src="/w3images/coffeehouse2.jpg" style="width:100%;max-width:1000px;margin-top:32px;"-->
      </div>
    </div>
  </div>

</template>

<script>
  import axios from 'axios'

  export default {

    data() {
      return {
        melding: "",
        kunder: [],
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
          this.kunder = response.data
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
        ).then(response => (this.kunder = response.data))
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